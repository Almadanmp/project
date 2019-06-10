import React, {Component} from 'react';
import {Card, CardBody, Col, Form, FormGroup, Input, Label, Table, Row} from "reactstrap";
import TableBody from "../../House/HouseConfiguration/SmartGrid/TableBody";
import US250GetSensors from "./US250/US250GetSensors";


class ListRooms extends Component {

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
    const token = localStorage.getItem('loginToken');
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


  render() {

    var {isLoaded, item} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      if (!item.error) {
        return (
          <>
            <Row>
            {item.map(items => (
              <Col xs="6" lg="4">
                <Card>
                  <CardBody>
                    <Table responsive>
                      <tr value={items.name} key={items.name}>
                        <h5>Room: {items.name}</h5>
                        <US250GetSensors roomID={items.name}/> </tr>
                    </Table>
                  </CardBody>
                </Card>
              </Col>
            ))}
            </Row>
          </>
        );
      } else {
        return null
      }
    }
  }
}

export default ListRooms;
