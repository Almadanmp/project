import React, {Component} from 'react';
import TableHeader from "../../../House/HouseConfiguration/TableHeader";
import {
  Badge,
  Card,
  CardBody,
  CardHeader,
  Col,
  ListGroup,
  ListGroupItem,
  ListGroupItemHeading,
  ListGroupItemText,
  Row,
  TabContent,
  TabPane, Table, Button, Collapse
} from 'reactstrap';
import US253 from "../US253/US253";
import SensorTypesSelect from "../US253/SensorTypesSelect";


class US250GetSensors extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      sensors: false,
      activeTab: 1,
      roomId: 0,
      collapse: false
    }
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/roomConfiguration/rooms/' + this.props.roomID + '/sensors', {
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
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {
    var {item} = this.state;
    return (
      <>
        {item.length > 0 ?
          <Table responsive>

            <thead>
            <tr>
              <th>Sensor</th>
              <th>Id</th>
              <th>Type</th>
              <th>Activation</th>
              <th>State</th>
            </tr>
            </thead>
            <tbody>

            {item.map(item => (
              <tr key={item.name}>
                <td>{item.name}</td>
                <td> {item.id}</td>
                <td>{item.type}</td>
                <td>{item.dateStartedFunctioning}</td>
                <td>{item.active == true ? <Badge color="success"> Active </Badge> :
                  <Badge color="danger"> Inactive </Badge>}</td>

              </tr>
            ))}


            </tbody>
          </Table> : "No sensors on this room."}
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#93c4c4', marginBottom: '2rem'}}
                  class="fa fa-plus-square-o fa-lg mt-4">Add Sensor</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
              <span>
                <SensorTypesSelect roomID={this.props.roomID}/>
              </span>
                <span>
              </span>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      </>
    );
  }

}

export default US250GetSensors;
