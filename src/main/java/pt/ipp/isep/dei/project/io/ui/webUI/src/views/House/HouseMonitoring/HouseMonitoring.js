import React, {Component} from 'react';
import US600 from "./US600";
import US620 from "./USRedux/US620Redux/US620";
import US630 from "./USRedux/US630Redux/US630Redux";
import US631 from "./USRedux/US631Redux/US631";
import US633Test from "./USRedux/US633Redux/US633Test";

class HouseMonitoring extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (

      <div>
        <h2>Welcome to the House Monitoring Menu.</h2>
        <h4>Please select the US you want to run.</h4>
        <US600/>
        <US620/>
        <US630/>
        <US631/>
        <US633Test/>
      </div>
    );
  }
}

export default HouseMonitoring;
