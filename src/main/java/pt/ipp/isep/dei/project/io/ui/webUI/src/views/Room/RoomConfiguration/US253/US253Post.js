import React, { Component } from 'react';


class US250Post extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    console.log(this.props);
    fetch('http://localhost:9898/roomConfiguration/rooms/'+this.props.roomID+'/sensors',{
      method: 'post',
        headers: {'Content-Type':'application/json'},
      body: {
        sensorId: this.props.sensorId,
        name: this.props.name,
        dateStartedFunctioning: this.props.dateStartedFunctioning,
        typeSensor: this.props.typeSensor
      }
    });
  };


  render() {
    return (
      <div>
        <p>The created sensor has the following configuration: {this.props.roomID}, {this.props.sensorId}, { this.props.name},{this.props.dateStartedFunctioning}, {this.props.typeSensor} </p>
      </div>
    );


}}

export default US250Post;
