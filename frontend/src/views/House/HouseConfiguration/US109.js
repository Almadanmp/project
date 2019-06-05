import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, Form, FormGroup, Label, Input, CardHeader, ListGroup} from 'reactstrap';
import US109Redux from "./US109Redux/US109Redux";
import US108Select from "../../Room/RoomConfiguration/US250/US108Select";
import US250Button from "../../Room/RoomConfiguration/US250/US250Button";
import US108Redux from "./US108Redux/US108Redux";

class US109 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      value: ''
    }
    this.handleChange = this.handleChange.bind(this);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
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

  toggle() {
    this.setState(state => ({ collapse: !state.collapse }));
  }

  render() {

    var {isLoaded, item} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      return (
        <div>
          <div>
            <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get a list of
              existing rooms. (US109)</Button>
            <Collapse isOpen={this.state.collapse}>
              <Card>
                <CardHeader>
                  <i className="fa fa-align-justify"></i><strong>List of Rooms</strong>
                </CardHeader>
                <CardBody>
                  <Form action="" method="post">
                    <FormGroup>
                      <Label>Select Room</Label>
                      <Input type="select" name="select" id="select" value={this.state.value}
                             onChange={this.handleChange}>
                        <option value="0" onChange={this.handleChange}>Please select</option>
                        {item.map(items => (
                          <option value={items.name} key={items.name}>
                            Name: {items.name}
                          </option>
                        ))}
                      </Input>
                    </FormGroup>
                  </Form>
                  <US109Redux roomID={this.state.value}/>
                </CardBody>
              </Card>
            </Collapse>
          </div>
        </div>
      );
    }
  }
}

export default US109;
