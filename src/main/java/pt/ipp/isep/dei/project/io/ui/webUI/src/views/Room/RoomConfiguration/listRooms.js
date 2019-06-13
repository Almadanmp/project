import React, {Component} from 'react';
import {
  Card,
  CardBody,
  Col,
  Form,
  FormGroup,
  Input,
  Label,
  Table,
  Row,
  ListGroup,
  ListGroupItem,
  ListGroupItemHeading,
  ListGroupItemText,
  CardHeader,
  Badge,
  TabContent, TabPane
} from "reactstrap";
import US250GetSensors from "./US250/US250GetSensors";


class ListRooms extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      value: '',
      activeTab: 1
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

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }

  render() {

    var {isLoaded, item} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      if (!item.error) {
        return (
          <>


              <Col>
                <Card>
                  <CardHeader>
                    <i className="fa fa-align-justify"></i><strong>Room Sensors</strong> <small>by room</small>
                    <div className="card-header-actions">
                    </div>
                  </CardHeader>
              <CardBody>
                <Row>
                  <Col xs="4">
                    {item.map(items => (
                    <ListGroup id="list-tab" role="tablist">
                        <ListGroupItem onClick={() => this.toggle(items.name)} action active={this.state.activeTab === items.name} >{items.name}</ListGroupItem>
                      <TabContent activeTab={this.state.activeTab}>
                        <TabPane tabId={items.name} >
                        <US250GetSensors roomID={items.name}/>
                        </TabPane>
                      </TabContent>
                    </ListGroup>
                    ))}
                  </Col>
                </Row>
              </CardBody>
              </Card>
              </Col>


          </>
        );
      } else {
        return null
      }
    }
  }
}

export default ListRooms;
