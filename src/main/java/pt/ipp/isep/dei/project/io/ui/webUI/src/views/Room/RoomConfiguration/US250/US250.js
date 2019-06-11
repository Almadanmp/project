import React, {Component} from 'react';
import {Button, Card, CardBody, Collapse} from 'reactstrap';
import US108Select from "./US108Select";
import ListRooms from "../listRooms"


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

                <ListRooms/>


        </div>
      );
  }

}

export default US250;
