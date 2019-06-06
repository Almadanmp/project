import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card} from 'reactstrap';
import US605SelectRoom from "./US605SelectRoom";

class US605 extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {
      item: [],
      showResults: false,
      isLoaded: false,
    }
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    var {id, item} = this.state;
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get Current Temperature
          in a room. (US605)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <US605SelectRoom/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );

  }
}

export default US605;


