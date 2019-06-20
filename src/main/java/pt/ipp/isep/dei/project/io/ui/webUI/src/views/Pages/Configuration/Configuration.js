import React, {Component} from 'react';
import FileUploaderOne from "./uploadGA/UploadGA";
import {Button, Card, CardBody, CardHeader, Col, Collapse, Row} from "reactstrap";

class Configuration extends Component {
  constructor(props) {
    super(props);
    this.state = {
      collapse: false
    }
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>

        <h2>Welcome to the Application Configuration Menu </h2>
        <p></p>
        <h5>Please select the option you want to run:</h5>
        <p></p>
        <p></p>
        <p></p>
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#93c4c4', marginBottom: '2rem'}}
                  class="fa fa-plus-square-o fa-lg mt-4">Import geographic areas and sensors</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
              <span>
              <FileUploaderOne/>
              </span>
                <span>
              </span>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      </div>
    );
  }
}


export default Configuration;

