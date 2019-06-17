import React, {Component} from 'react';
import {Button, Col, Container, Input, InputGroup, InputGroupAddon, InputGroupText, Row} from "reactstrap";

class PleaseLogin extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = { collapse: false };
  }

  toggle() {
    this.setState(state => ({ collapse: !state.collapse }));
  }

  render() {
    return (
      <div className="app flex-row align-items-center">
        <Container>
          <Row className="justify-content-start">
            <Col md="8">
              <span className="clearfix">
                <h4 className="pt-12">You Shall Not Pass!</h4>
                <p className="text-muted float-left">Please log in to access this page.</p>
                <img src="https://i.imgur.com/qVPAMy3.png" width="600" height="400"/>
              </span>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default PleaseLogin;

