import React, {Component} from 'react';
import {Collapse, Button, CardBody, Card, Col, Row} from 'reactstrap';
import DatePickerOneDay610 from "./DatePickerOneDay610";
import SelectRoom from "./SelectRoom"

class US610 extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {
      collapse: false,
      selectedDay:''
    };
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  handleDayPicker = (selectedDay) => {

    console.log("handleDayPicker:"+ JSON.stringify(selectedDay))
    if (selectedDay !== undefined) {
      const initialDay = selectedDay.toISOString().substring(0, 10);
      this.setState({selectedDay: initialDay});
    }
  }

  render() {
    const numberOfMonths = 1;
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get the maximum temperature in a room in a given day. (US610)</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <span>
              <DatePickerOneDay610 getDays={this.handleDayPicker} numberOfMonths={numberOfMonths}/>
              </span>
              <SelectRoom day={this.state.selectedDay}/>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

export default US610;
