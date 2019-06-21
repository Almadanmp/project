import React, {Component} from 'react';
import US005extra from "./US005extra";
import US005 from "./US005";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";

class SensorMenu extends Component {
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
        <Row><Col>
          <h2>Welcome to the Sensor Menu</h2>
        </Col></Row>
        <Row><Col>
          <h4>Please select the option you want to run</h4>
        </Col></Row>
        <Row><Col>
          <US005/>
        </Col></Row>
        <Row><Col>
          <US005extra/>
        </Col></Row>
      </div>
    );
  }
}

export default SensorMenu;
