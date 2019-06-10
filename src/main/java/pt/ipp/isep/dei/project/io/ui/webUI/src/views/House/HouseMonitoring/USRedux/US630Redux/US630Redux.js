import React, {Component} from 'react';
import {fetchColdDay} from './Actions630';
import {Alert, Button, Card, CardBody, Collapse} from "reactstrap";
import DatePickerWithTwoDates from "../../DatePickerWithTwoDates";
import connect from "react-redux/es/connect/connect";

class US630 extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {
      collapse: false,
      from: undefined,
      to: undefined
    };
  }

  componentDidMount() {
    this.props.onFetchColdDay(this.state.from, this.state.to);
  }

  handleIntervalPicker = (from, to) => {

    console.log("handleIntervalPicker: from" + JSON.stringify(from) + "to: " + JSON.stringify(to))
    if (from !== undefined && to !== undefined) {
      const initialDay = from.toISOString().substring(0, 10);
      const finalDay = to.toISOString().substring(0, 10);
      this.setState({from: from, to: to});
      this.props.onFetchColdDay(initialDay, finalDay)

    }
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    const numberOfMonths = 2;
    const {loading} = this.props;
    const {cold} = this.props;
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    } else {
      if (localStorage.getItem("user").includes("admin")) {
        return (
          <>
            <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.toggle}
                    style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get the last coldest
              day (lower maximum temperature) in the house area in a given period.</Button>
            <Collapse isOpen={this.state.collapse}>
              <Card>
                <CardBody>
                  <Alert color="danger">ERROR: Non-authorized user.</Alert>
                </CardBody>
              </Card>
            </Collapse>
          </>
        );
      } else {
        return (
          <>
            <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.toggle}
                    style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get the last coldest
              day (lower maximum temperature) in the house area in a given period.</Button>
            <Collapse isOpen={this.state.collapse}>
              <Card>
                <CardBody>
                  <DatePickerWithTwoDates getDates={this.handleIntervalPicker} numberOfMonths={numberOfMonths}/>
                  <h5 key={cold.value}>The coldest day was {cold.date} and the temperature was {cold.value} ºC </h5>
                </CardBody>
              </Card>
            </Collapse>
          </>
        );
      }
    }
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducers630.loading,
    cold: state.Reducers630.cold,
    error: state.Reducers630.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchColdDay: (from, to) => {
      dispatch(fetchColdDay({from, to}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US630);
