import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchAmplitude} from './Actions';
import {Button, Card, CardBody, Collapse} from "reactstrap";
import DatePickerWithTwoDates from "../../DatePickerWithTwoDates";

class US633Test extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {
      collapse: false,
      from: undefined,
      to:undefined
    };
  }

  componentDidMount() {
    this.props.onFetchAmplitude(this.state.from, this.state.to);
  }

  handleIntervalPicker = (from, to) => {

    console.log("handleIntervalPicker: from"+ JSON.stringify(from)+ "to: "+ JSON.stringify(to))
    if (from !== undefined && to !== undefined) {
      const initialDay = from.toISOString().substring(0, 10);
      const finalDay = to.toISOString().substring(0, 10);
      this.setState({from: from, to: to});
      this.props.onFetchAmplitude(initialDay, finalDay)

    }
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    const numberOfMonths = 2;
    const {loading} = this.props;
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    }
    else {
      const{amplitude} = this.props;
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get the day with the
            highest temperature amplitude in the house area in a given period. (US633)</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <DatePickerWithTwoDates getDates={this.handleIntervalPicker} numberOfMonths={numberOfMonths}/>
                <h5 key={amplitude.value}>The highest amplitude was {amplitude.value} on the date {amplitude.date}</h5>
                </CardBody>
            </Card>
          </Collapse>
        </div>
      );
    }
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducers633.loading,
    amplitude: state.Reducers633.amplitude,
    error: state.Reducers633.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchAmplitude: (from, to) => {
      dispatch(fetchAmplitude({from, to}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US633Test);
