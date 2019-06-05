import React, {Component} from 'react';
import GridRoomRemover from "./US149/GridRoomRemover"
import {
  Collapse,
  Button,
  CardBody,
  Card,
  CardHeader,
} from 'reactstrap';

class US149 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Remove a room from a grid. (US149)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardHeader>
              <strong>US 149</strong>
            </CardHeader>
            <CardBody>
              <GridRoomRemover/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US149;
