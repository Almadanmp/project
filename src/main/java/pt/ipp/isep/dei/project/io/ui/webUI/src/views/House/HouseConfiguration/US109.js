import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, Form, FormGroup, Label, Input} from 'reactstrap';
import US108Select from "./US109/US108Select";

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
    const token = localStorage.getItem('loginToken')
    fetch('https://localhost:8443/houseSettings/houseRooms',{
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

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {

    var {isLoaded} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      return (
        <div>
          <div>
            <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Edit the
              configuration of an existing room. (US109)</Button>
            <Collapse isOpen={this.state.collapse}>
              <Card>
                <CardBody>
                  <Form action="" method="post">
                    <FormGroup>
                      <US108Select/>
                    </FormGroup>
                  </Form>
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
