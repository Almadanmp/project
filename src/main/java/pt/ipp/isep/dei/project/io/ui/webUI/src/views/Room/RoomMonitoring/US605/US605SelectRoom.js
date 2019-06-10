import React, {Component} from 'react';
import {Card, CardBody, Col, Form, FormGroup, Input, Label, Table, Row, CardHeader} from "reactstrap";
import US605Button from "./US605Button";
import US605GetCurrentTemperature from "./US605GetCurrentTemperature";

class US605SelectRoom extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: 0,
      isLoaded: false,
      value: '',
      link: ''
    }
    this.handleChange = this.handleChange.bind(this);
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


  render() {

    var {isLoaded, item} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      if (!item.error) {
      return (
        <>
          <Row>
            <Col >
              <Card className="card-accent-warning">
                <CardHeader>
                  Current Temperature
                </CardHeader>
                <CardBody>
                  <Table responsive>
                      <CardBody>


                        <tr>
                          <th>Room</th>
                          <th>Temperature</th>
                        </tr>

                        {item.map(items => (
                        <tr>
                            <td value={items.name} key={items.name}> {items.name} </td>
                            <td> <US605GetCurrentTemperature roomID={items.name}/> </td>
                        </tr>
                        ))}

                      </CardBody>
                  </Table>
                </CardBody>
                    </Card>
                  </Col>
          </Row>

        </>
      );
    } else {
      return "ERROR: Non-authorized user."
    }
    }
  }
}

export default US605SelectRoom;
