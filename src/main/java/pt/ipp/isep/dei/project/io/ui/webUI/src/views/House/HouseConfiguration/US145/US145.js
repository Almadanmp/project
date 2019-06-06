import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card} from 'reactstrap';
import US145Select from "./US145Select";


class US145 extends Component {


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
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}> Get a list of existing
          rooms attached to a house grid. (US145)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <US145Select/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US145;
