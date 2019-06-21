import React, {Component} from 'react';
import US101Redux from './US101Redux/US101Redux';
import US105 from './US105';
import US108Redux from "./US108/US108Redux";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";

class HouseConfiguration extends Component {
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
          <h2>Welcome to the House Configuration Menu</h2>
        </Col>
        </Row>
        <Row><Col>
          <h4>Please select the option you want to run</h4>
        </Col></Row>
        <Row><Col>
          <US108Redux/>
        </Col>
        </Row>
        <Row><Col>
          <US105/>
        </Col>
        </Row>
        <Row><Col>
          <US101Redux/>
        </Col>
        </Row>
      </div>
    );
  }
}

export default HouseConfiguration;
