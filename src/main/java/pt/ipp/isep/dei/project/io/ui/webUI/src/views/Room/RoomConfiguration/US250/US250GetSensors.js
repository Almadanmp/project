import React, { Component } from 'react';
import TableHeader from "../../../House/HouseConfiguration/SmartGrid/TableHeader";


class US250GetSensors extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      sensors:false,
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/roomConfiguration/rooms/'+this.props.roomID+'/sensors',{
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"
        }
      }
    )
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {
    const headers = {
      name: "Sensors",
      id: "ID",
      type: "Type",
    };
    var {item} = this.state;
    return (
      <>
        <TableHeader headers={headers}/>
            {item.map(item => (
              <tr key={item.name}>
                <td>{item.name} </td>
                <td>{item.id} </td>
                <td>{item.type} </td>
              </tr>
            ))}
      </>
    );
  }

}

export default US250GetSensors;
