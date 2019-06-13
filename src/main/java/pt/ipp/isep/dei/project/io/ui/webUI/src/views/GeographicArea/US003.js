import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card} from 'reactstrap';
import US003Redux from "./US003/US003Redux";

class US003 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Add geographic area</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <US003Redux/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }

}

export default US003;
