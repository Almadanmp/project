import React, {Component} from 'react';
import {Collapse, CardBody, Card} from 'reactstrap';
import US005extraRedux from "./US005Extra/US005extraRedux";

import './button.js'

class US005extra extends Component {
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
        <button className="bubbly-button" onClick={this.toggle} >List of sensor types</button>
        <Collapse isOpen={this.state.collapse}>
            <CardBody>
              <US005extraRedux/>
            </CardBody>
        </Collapse>
      </div>
    );
  }

}

export default US005extra;




