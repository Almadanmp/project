import React, {Component} from 'react';
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
  CardFooter
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
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Create a new house
          grid. (US130)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardHeader>
              <strong>US 130</strong>
            </CardHeader>
            <CardBody>
              <Form action="" method="post">
                <FormGroup>
                  <Label htmlFor="nf-email">Designation of the new Energy Grid</Label>
                  <Input type="text" placeholder="Enter Designation.."/>
                </FormGroup>
                <FormGroup>
                  <Label htmlFor="nf-password">Insert the maximum contracted power</Label>
                  <Input type="number" placeholder="Enter Maximum Contracted Power.."/>
                </FormGroup>
              </Form>
            </CardBody>
            <CardFooter>
              <Button type="submit" size="sm" color="primary"><i className="fa fa-dot-circle-o"></i> Submit</Button>
              <Button type="reset" size="sm" color="danger"><i className="fa fa-ban"></i> Reset</Button>
            </CardFooter>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US130;
