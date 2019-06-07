import React, {Component} from 'react';
import EnergyGridCreator from "./US130/EnergyGridCreator";
import {
  Collapse,
  Button,
  CardBody,
  Card,
  CardHeader,
} from 'reactstrap';

class US130 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Create a new house
          grid.</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <EnergyGridCreator/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );}
    else{
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Create a new house
            grid.</Button>
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

export default US130;
