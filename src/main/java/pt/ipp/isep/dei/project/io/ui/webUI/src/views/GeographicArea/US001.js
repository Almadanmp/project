import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, CardHeader} from 'reactstrap';
import US001Redux from "./US001/US001Redux";

class US001 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Add a Type of
          Geographic Area</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card style={{size: 10}}>
            <CardHeader>
              Create a new Type
            </CardHeader>
            <CardBody>
              <US001Redux/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }

}

export default US001;
