import React, {Component} from 'react';


class SaveAreaSensor extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }


  componentDidMount() {
    const token = localStorage.getItem('loginToken')
    const sensorId = this.props.sensorId;
    const name = this.props.name;
    const dateStartedFunctioning = this.props.dateStartedFunctioning;
    const typeSensor = this.props.typeSensor;
    const latitude = this.props.latitude;
    const longitude = this.props.longitude;
    const altitude = this.props.altitude;

    console.log(this.props);
    fetch('https://localhost:8443/sensorsettings/areas/' + this.props.geographicAreaId + '/sensors', {
      method: 'post',
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({sensorId, name, dateStartedFunctioning, typeSensor, latitude, longitude, altitude})
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log);
  };


  render() {
    return (
      <div>
        <p>The created sensor has the following
          configuration: {this.props.geographicAreaId}, {this.props.sensorId}, {this.props.name},{this.props.dateStartedFunctioning}, {this.props.typeSensor} </p>
      </div>
    );


  }
}

export default SaveAreaSensor;
