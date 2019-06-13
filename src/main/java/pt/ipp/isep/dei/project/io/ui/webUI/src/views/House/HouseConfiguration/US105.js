import React, {Component} from 'react';
import RoomCreator from "./US105/RoomCreator"
import {
  Collapse,
  Button,
  CardBody,
  Card,
  CardHeader, Alert,
} from 'reactstrap';

class US105 extends Component {
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
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Add a new room to the
            house.</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <RoomCreator/>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      )
    }
}

export default US105;
