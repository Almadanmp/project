import React, { Component } from 'react';
import {
  Collapse,
  Button,
  CardBody,
  Card,
  CardHeader,
  Form,
  FormGroup,
  Label,
  Input,
  FormText,
  CardFooter, ListGroup, ListGroupItem, Badge
} from 'reactstrap';
import US108Redux from "./US108Redux/US108Redux.js";

class US108 extends Component {
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
        <Button onClick={this.toggle} style={{ backgroundColor: '#FFFFFF', marginBottom: '1rem' }}>Get a list of existing rooms. (US108)</Button>
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
    );
  }
}

export default US108;
