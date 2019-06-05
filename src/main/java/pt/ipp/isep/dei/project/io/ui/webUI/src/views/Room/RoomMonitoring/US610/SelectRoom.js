import React, {Component} from 'react';
import {Form, FormGroup, Input} from "reactstrap";
import US610Button from "./US610Button";

class SelectRoom extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      value: '',
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

  handleProperties(){
    return {
      roomID: this.state.value,
      day: this.props.day
    };
  }

  render() {
    console.log(this.handleProperties())
    var {isLoaded, item} = this.state;

    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      return (
        <div>
          <Form action="" method="post" >
            <FormGroup>
              <h6>Select a Room</h6>
              <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>
                <option value="0" onChange={this.handleChange}>Please select</option>
                {item.map(items => (
                  <option value={items.name}  key={items.name}>
                    Name: {items.name}
                  </option>
                ))}
              </Input>
            </FormGroup>
          </Form>

          <US610Button day={this.props.day} roomID={this.state.value}/>
        </div>
      );
    }
  }
}

export default SelectRoom;
