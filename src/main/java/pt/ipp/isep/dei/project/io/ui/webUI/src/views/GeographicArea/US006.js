import React, {Component} from 'react';
import {Button, Card, CardBody, Collapse} from 'reactstrap';
import SelectSensor from "./US006/SelectSensor";

class US006 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Add a new
          sensor to a Geographic Area</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <span>
                <SelectSensor/>
              </span>
              <span>
              </span>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US006;
