import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, Form, FormGroup, Label, Input, Alert} from 'reactstrap';
import US108Select from "../US109/US108Select";

class  US108Collapse extends Component {
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
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Edit</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <Form action="" method="post">
                  <FormGroup>
                    <US108Select/>
                  </FormGroup>
                </Form>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      </div>
    );
  }
}

export default US108Collapse;
