import React, {Component} from 'react';
import {fetchNoData} from "../../../EnergyGrid/US147/Actions";
import {fetchSensorInfoFailure} from "./Actions";


class US253Post extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      error: ''
    }
  }


  componentDidMount() {
    const token = localStorage.getItem('loginToken')
    const sensorId = this.props.sensorId;
    const name = this.props.name;
    const dateStartedFunctioning = this.props.dateStartedFunctioning;
    const typeSensor = this.props.typeSensor;
    fetch('https://localhost:8443/rooms/' + this.props.roomID + '/sensors', {
      method: 'post',
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({sensorId, name, dateStartedFunctioning, typeSensor})
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(err => {
        if (err.response === 400) {
          this.setState({error: (err.message)})
        }
        else {
          if (err.response !== undefined) {
            this.setState({error: (err.response.data)});
          }
        }
      })
  };


  render() {
    const {error} = this.state
    console.log(error)
    if (error!==undefined){
      return(<div>{error}</div>)
    }
    else {
      return (
        <div>
          <p>The sensor {this.props.name} has been created in the room {this.props.roomID}. </p>
        </div>
      );
    }

  }
}

export default US253Post;
