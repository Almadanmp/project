import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card} from 'reactstrap';
import US147SelectRoom from "./US147SelectRoom";
import US147SelectGrid from "./US147SelectGrid";


class US147 extends Component {


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
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}> I want to attach a
          room to a house grid, so that the room’s power and energy consumption is included in that grid. (US147)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <US147SelectRoom/>
              <US147SelectGrid/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }

}

export default US147;
