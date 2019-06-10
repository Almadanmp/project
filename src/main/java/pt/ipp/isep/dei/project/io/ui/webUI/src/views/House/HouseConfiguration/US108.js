import React, {Component} from 'react';
import {
  Collapse,
  Button,
  CardBody,
  Card,
  CardHeader, ListGroup, Alert,
} from 'reactstrap';
import US108Redux from "./US108/US108Redux.js";

class US108 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get a list of existing
          rooms.</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardHeader>
              <i className="fa fa-align-justify"></i><strong>List of Rooms</strong>
            </CardHeader>
            <CardBody>
              <ListGroup>
                <US108Redux/>
              </ListGroup>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );}
    else{
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get a list of existing
            rooms.</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <p><Alert color="danger">ERROR: Non-authorized user.</Alert></p>
              </CardBody>
            </Card>
          </Collapse>
        </div>)

    }
  }
}

export default US108;
