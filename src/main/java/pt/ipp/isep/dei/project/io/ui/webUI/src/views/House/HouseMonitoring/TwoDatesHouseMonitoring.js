import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchHottestDay} from './USRedux/US631Redux/Actions631';
import {Alert, Button, Card, CardBody, Collapse} from "reactstrap";
import DatePickerWithTwoDates from "./DatePickerWithTwoDates";
import {fetchColdDay} from "./USRedux/US630Redux/Actions630";
import {fetchAmplitude} from './USRedux/US633Redux/Actions633';

class US631 extends Component {
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
    this.props.onFetchHottestDay(this.state.from, this.state.to);
    this.props.onFetchColdDay(this.state.from, this.state.to);
    this.props.onFetchAmplitude(this.state.from, this.state.to);
  }

  handleIntervalPicker = (from, to) => {

    console.log("handleIntervalPicker: from" + JSON.stringify(from) + "to: " + JSON.stringify(to));
    if (from !== undefined && to !== undefined) {
      const initialDay = from.toISOString().substring(0, 10);
      const finalDay = to.toISOString().substring(0, 10);
      this.setState({from: from, to: to});
      this.props.onFetchHottestDay(initialDay, finalDay);
      this.props.onFetchColdDay(initialDay, finalDay);
      this.props.onFetchAmplitude(initialDay, finalDay)
    }
  };

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    const numberOfMonths = 2;
    const {loading, cold, hottestDay, amplitude, error} = this.props;
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    }
    else {
      if (localStorage.getItem("user").includes("admin")) {
        return (
          <div>
            <Card>
              <CardBody>
                <Alert color="danger"> ERROR: Non-authorized user </Alert>
              </CardBody>
            </Card>
          </div>
        )
      }
      else{
        return(
          <>

          <CardBody>
          <DatePickerWithTwoDates getDates={this.handleIntervalPicker} numberOfMonths={numberOfMonths}/>
            <p>
            <h5 key={hottestDay.value}>The hottest day was  <mark>{hottestDay.date}</mark> and the temperature
          was {hottestDay.value} ºC</h5>
            </p>
              <p>
            <h5 key={cold.value}>The coldest day was <mark >{cold.date }</mark> and the temperature was {cold.value} ºC </h5>
              </p>
                <p>
            <h5 key={amplitude.value}>The highest amplitude was <mark>{amplitude.value}</mark> on the date {amplitude.date}</h5>
                </p>
        </CardBody>

      </>

        );
      }
    }
}
}


const mapStateToProps = (state) => {
  return {
    loading: state.Reducers631.loading,
    hottestDay: state.Reducers631.hottestDay,
    errorHot: state.Reducers631.error,
    cold: state.Reducers630.cold,
    amplitude: state.Reducers633.amplitude,
    errorCold: state.Reducers630.error,
    errorAmplitude: state.Reducers633.error,
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchHottestDay: (from, to) => {
      dispatch(fetchHottestDay({from, to}) )
    },
    onFetchColdDay: (from, to) => {
      dispatch(fetchColdDay({from, to}))
    },
    onFetchAmplitude: (from, to) => {
      dispatch(fetchAmplitude({from, to}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US631);
