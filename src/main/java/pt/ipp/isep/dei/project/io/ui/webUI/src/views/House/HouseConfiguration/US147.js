import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, CardHeader, Form, FormGroup, Label, Input, CardFooter} from 'reactstrap';
import AttachRoomToGrid from "./US147/AttachRoomToGrid";

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
    if(localStorage.getItem("user").includes("admin")){
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Attach a room to a
          house grid.</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <AttachRoomToGrid/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );}
    else{
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Attach a room to a
            house grid. </Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <p>ERROR: Non-authorized user.</p>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      )

    }
  }
}

export default US147;
