import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, Col, Row} from 'reactstrap';
import US108Select from "./US108Select";
import SensorTypesSelect from "./SensorTypesSelect";

class US253 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>I want to add a new
          sensor to a room from the list of available sensor types, in order to configure it. (US253)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <span>
                <US108Select/>
                <SensorTypesSelect/>
              </span>
              <span>
              <p>
                <input style={{display: "inline-block"}} type="button" value="Submit"/>
              </p>
              </span>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US253;
