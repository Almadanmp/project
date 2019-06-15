import React, { Component } from 'react';
import ListRooms from "./listRooms";

class RoomConfiguration extends Component {
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
        <h2>Welcome to the Room Configuration Menu</h2>
        <h4>Please select the option you want to run</h4>
        <ListRooms/>
      </div>
    );
  }
}

export default RoomConfiguration;
