import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card} from 'reactstrap';
import ReduxUS004 from "./US004/ReduxUS004";

class US004 extends Component {
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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get geographic areas of _ type</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <ReduxUS004/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }

}

export default US004;
