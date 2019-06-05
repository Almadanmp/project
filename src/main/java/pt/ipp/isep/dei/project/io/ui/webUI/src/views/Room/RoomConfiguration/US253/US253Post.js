import React, { Component } from 'react';


class US250Post extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    fetch('http://localhost:9898/roomConfiguration/rooms/'+this.props.roomID+'/sensors',{
      method: 'post',
        headers: {'Content-Type':'application/json'},
      body: {
        sensorId: this.props.sensorId,
        name: this.props.name,
        dateStartedFunctioning: this.props.dateStartedFunctioning,
        typeSensor: this.state.typeSensor
      }
    });
  };


  render() {
    return (
      <div>

      </div>
    );


}}

export default US250Post;
