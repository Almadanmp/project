import React, { Component } from 'react';
import TableHeader from "../../../House/HouseConfiguration/TableHeader";
import { Badge, Card, CardBody, CardHeader, Col, ListGroup, ListGroupItem, ListGroupItemHeading, ListGroupItemText, Row, TabContent, TabPane } from 'reactstrap';


class US250GetSensors extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      sensors:false,
      activeTab: 1
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/roomConfiguration/rooms/'+this.props.roomID+'/sensors',{
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
    const headers = {
      name: "Sensors",
      id: "ID",
      type: "Type",
    };
    var {item} = this.state;
    return (
      <>

            {item.map(item => (

                <p>{item.name} {item.id} {item.type}</p>

            ))}

      </>
    );
  }

}

export default US250GetSensors;
