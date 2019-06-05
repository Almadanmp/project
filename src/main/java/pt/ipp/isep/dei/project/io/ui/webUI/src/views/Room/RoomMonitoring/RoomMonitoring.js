import React, { Component } from 'react';

import US610 from "./US610/US610";
import US605 from "./US605/US605";

class RoomMonitoring extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = { collapse: false };
  }

  toggle() {
    this.setState(state => ({ collapse: !state.collapse }));
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Room Monitoring Menu.</h2>
        <h4>Please select the US you want to run.</h4>
        <US605/>
        <US610/>
      </div>
    );
  }
}

export default RoomMonitoring;
