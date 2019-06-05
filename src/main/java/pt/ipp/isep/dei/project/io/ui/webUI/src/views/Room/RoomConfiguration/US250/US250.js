import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, Form, FormGroup, Label, Input} from 'reactstrap';
import US108Select from "./US108Select";
import US250Button from "./US250Button";


class US250 extends Component {


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


  handleClick() {
    this.setState({showResults: true});
  }


  render() {
    var {id, item} = this.state;
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>I want to get a list of
          all sensors in a room, so that I can configure them. (US250)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <US108Select/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }

}

export default US250;
