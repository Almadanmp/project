import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, CardHeader, Form, FormGroup, Label, Input, CardFooter} from 'reactstrap';
import AttachRoomToGrid from "./US147Redux/AttachRoomToGrid";

class US147 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Attach a room to a
          house grid. (US147)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardHeader>
              <strong>US 147</strong>
            </CardHeader>
            <CardBody>
              <AttachRoomToGrid/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US147;
