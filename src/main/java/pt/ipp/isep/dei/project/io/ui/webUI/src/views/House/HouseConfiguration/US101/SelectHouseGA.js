import React, {Component} from 'react';
import {Form, FormGroup, Input, Label} from "reactstrap";
import SelectHouseAddressLocation from './SelectHouseAddressLocation';

class SelectHouseGA extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      value: ''
    };
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/geographic_area_settings/areas', {
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"
        }
      }
    )
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

    const {isLoaded, item} = this.state;
    console.log(item);
    this.state.item = Array.from(this.state.item);
    if (!isLoaded) {
      return <div>Loading
        ...</div>
    } else {

      if (!item.error) {
        return (
          <div>
            <Form action="" method="post">
              <FormGroup>
                <Label>Select Geographic Area</Label>
                <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>
                  <option value="0" onChange={this.handleChange}>Please select</option>
                  {item.map(items => (
                    <option value={items.geographicAreaId} key={items.geographicAreaId}>
                      Name: {items.name}
                    </option>
                  ))}
                </Input>
              </FormGroup>
            </Form>
            <SelectHouseAddressLocation geographicAreaId={this.state.value}/>
          </div>
        );
      } else {
        return "ERROR: Non-authorized user."
      }
    }
  }
}

export default SelectHouseGA;
