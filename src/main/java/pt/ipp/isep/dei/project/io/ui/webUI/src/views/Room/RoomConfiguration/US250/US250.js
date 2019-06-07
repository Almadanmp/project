import React, {Component} from 'react';
import {Button, Card, CardBody, Collapse} from 'reactstrap';
import US108Select from "./US108Select";


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
    if (localStorage.getItem("user").includes("admin")) {
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>I want to get a list
            of
            all sensors in a room, so that I can configure them. </Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <US108Select/>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      );
    } else {
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>I want to get a list
            of
            all sensors in a room, so that I can configure them. </Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <p>ERROR: Non-authorized user. </p>
              </CardBody>
            </Card>
          </Collapse>
        </div>)
    }
  }

}

export default US250;
