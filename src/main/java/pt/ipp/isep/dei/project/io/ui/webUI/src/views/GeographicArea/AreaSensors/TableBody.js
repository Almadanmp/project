import React, {Component} from 'react';
import {Badge} from "reactstrap";
import AreaSensorInactivation from "./DeactivateSensor/AreaSensorInactivation";
class TableBody extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    const{link} =this.props
    console.log(link)
    const token = localStorage.getItem('loginToken');
    fetch(link.href, {
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
          item: json,
        })
      })
      .catch(console.log)
    console.log(this.state.item);


  }

  render() {

    var {item} = this.state;
    return (
      <>
        <thead>
        <tr>
          <th>Sensor</th>
          <th>Type</th>
          <th>Activation</th>
          <th>State</th>
          <th>Deactivate</th>
          <th>Remove</th>
        </tr>
        </thead>
        <tbody>

        {item.map(item => (
          <tr key={item.name}>
            <td>{item.name}</td>
            <td>{item.type}</td>
            <td>{item.dateStartedFunctioning}</td>
            <td>{item.active == true ? <Badge color="success"> Active </Badge> :
              <Badge color="danger"> Inactive </Badge>}</td>
            <AreaSensorInactivation link={item.links.find((hrefs) => hrefs.rel === 'Deactivate this Sensor')} sensorId={item.name}/>
          </tr>
        ))}


        </tbody>
      </>
    );
  }

}



export default TableBody;
