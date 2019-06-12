import React, {Component} from 'react';
import TableHeader from "./TableHeader";
import {Button} from "reactstrap";
import removeFromGrid from "./gridRedux/removeFromGrid"

class TableBody extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      sensors: false,
      gridID: ''
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/gridSettings/grids/' + this.props.gridID, {
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          sensors: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {
    const headers = {
      name: "Rooms",
      floor: "Floor",
      height: "Height (m)",
      length: "Length (m)",
      width: "Width (m)",
      remove: "Remove from grid",
    };
    var {id, item} = this.state;
    return (
      <>
        <TableHeader headers={headers}/>
        {item.map(item => (
          <tr key={item.name}>
            <td style={{
              textAlign: "center"
            }}> {item.name}</td>
            <td style={{
              textAlign: "center"
            }}>{item.floor} </td>
            <td style={{
              textAlign: "center"
            }}> {item.height}</td>
            <td style={{
              textAlign: "center"
            }}> {item.length} </td>
            <td style={{
              textAlign: "center"
            }}> {item.width} </td>
            <td style={{
              textAlign: "center"
            }}>
              <Button style={{backgroundColor: '#ffffff', marginBottom: '1rem'}}><i
                class="fa fa-minus-square-o fa-lg"></i> </Button>
              {<removeFromGrid gridID={this.props.gridID} roomID={item.name}/>}
            </td>
          </tr>
        ))}
      </>
    );
  }

}


export default TableBody;
