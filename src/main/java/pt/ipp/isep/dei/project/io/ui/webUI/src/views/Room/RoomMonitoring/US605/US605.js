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

              <US605SelectRoom/>

      </div>
    );

  }
}

export default US605;


