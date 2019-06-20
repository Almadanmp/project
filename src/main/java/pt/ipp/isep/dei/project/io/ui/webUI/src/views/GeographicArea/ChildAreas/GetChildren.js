import React, {Component} from 'react';
import {Button, Card, CardBody, CardHeader, Col, Collapse, Table} from "reactstrap";
import TableBody from "../ChildAreas/TableBody";

class GetChildren extends Component {

  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {

    return (
      <>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Child Areas</Button>
        <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardHeader>
                <strong>Child Geographic Area</strong>
              </CardHeader>
              <CardBody style={{
                textAlign: "right"
              }}>
                <Table responsive>
                  <TableBody link={this.props.link}/>
                </Table>
                {/*<RemoveChildArea/>*/}
              </CardBody>
            </Card>
        </Collapse>
      </>

    )
  }

}


export default GetChildren;
