import React, {Component} from 'react';
import {Button, Col, Container, Input, InputGroup, InputGroupAddon, InputGroupText, Row} from "reactstrap";

class UnderMaintenance extends Component {
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
      <div>

        <Container>
          <Row className="justify-content-start">
            <Col md="15">
              <span className="clearfix">
        <h2 className="pt-12">The dev team!</h2>
        <p>Meet everyone involved in the making of the SmartHome Web app.</p>
<img src={"https://imgur.com/U3cTeGN.png"}/>
        <div className={"divEagle"}>
          <img src="https://imgur.com/ICquRJa.png" width="9%" height="9%"/>
          <h3>André</h3>
          <p>Benfica</p>
        </div>

        <div className={"divFox"}>
          <img src="https://imgur.com/MIORjL7.png" width="9%" height="9%"/>
          <h3>Cárina</h3>
          <p>Kitties</p>
        </div>

        <div className={"divHedgehog"}>
          <img src="https://imgur.com/a78ZEfT.png" width="9%" height="9%"/>
          <h3>Daniel</h3>
          <p>Sporting</p>
        </div>

        <div className={"divLion"}>
          <img src="https://imgur.com/7yG7o0m.png" width="9%" height="9%"/>
          <h3>Daniela</h3>
          <p>Death</p>
        </div>

        <div className={"divSnake"}>
          <img src="https://imgur.com/O0UJCta.png" width="9%" height="9%"/>
          <h3>João</h3>
          <p>K-pop</p>
        </div>

        <div className={"divTurtle"}>
          <img src="https://imgur.com/VzeQmTf.png" width="12%" height="9%"/>
          <h3>Maria</h3>
          <p>Communism</p>
        </div>

        <div className={"divDog"}>
        <img src="https://imgur.com/5bkoNJE.png" width="9%" height="9%"/>
        <h3>Miguel</h3>
          <p>Porto</p>
        </div>
                <div className={"divKoala"}>
        <img src="https://imgur.com/ezHuUEn.png" width="9%" height="9%"/>
        <h3>Nuno</h3>
                  <p>Dark</p>
                </div>
                <div className={"divHorse"}>
        <img src="https://imgur.com/GTWerXw.png" width="9%" height="9%"/>
        <h3>Teresa</h3>
                  <p>Filipinos</p>
                </div>
              </span>
            </Col>
          </Row>
        </Container>

      </div>
    );
  }
}

export default UnderMaintenance;

