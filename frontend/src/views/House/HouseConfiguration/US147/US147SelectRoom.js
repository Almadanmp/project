import React, {Component} from 'react';
import {Form, FormGroup, Input, Label} from "reactstrap";
import US147Button from "./US147Button";

class US147SelectRoom extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      value: ''
    }
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    fetch('http://localhost:9898/houseSettings/houseRooms')
      .then(res => res.json())
      .then((json) => {
        this.setState({
          isLoaded: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }


  render() {

    var {isLoaded, item} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      return (
        <div>
          <Form action="" method="post" >
            <FormGroup>
              <Label>Select Room</Label>
              <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>
                <option value="0" onChange={this.handleChange}>Please select</option>
                {item.map(items => (
                  <option value={items.name}  key={items.name}>
                    Room ID: {items.name}
                  </option>
                ))}
              </Input>
            </FormGroup>
          </Form>
          <US147Button roomID={this.state.value}/>
        </div>
      );
    }
  }
}

export default US147SelectRoom;
