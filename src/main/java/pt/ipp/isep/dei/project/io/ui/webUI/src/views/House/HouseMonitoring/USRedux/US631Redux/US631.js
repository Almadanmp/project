import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchHottestDay} from './Actions';
import {Button, Card, CardBody, Collapse} from "reactstrap";
import DatePickerWithTwoDates from "../../DatePickerWithTwoDates";

class US631 extends Component {
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
    this.props.onFetchHottestDay(this.state.from, this.state.to);
  }

  handleIntervalPicker = (from, to) => {

    console.log("handleIntervalPicker: from"+ JSON.stringify(from)+ "to: "+ JSON.stringify(to))
    if (from !== undefined && to !== undefined) {
      const initialDay = from.toISOString().substring(0, 10);
      const finalDay = to.toISOString().substring(0, 10);
      this.setState({from: from, to: to});
      this.props.onFetchHottestDay(initialDay, finalDay)

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
      const{hottestDay} = this.props;
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get the first hottest
            day (higher maximum temperature) in the house area in a given period. (US631)</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <DatePickerWithTwoDates getDates={this.handleIntervalPicker} numberOfMonths={numberOfMonths}/>
                <h5 key={hottestDay.value}>The hottest day was {hottestDay.date} and the temperature was {hottestDay.value} ºC</h5>
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
    loading: state.Reducers631.loading,
    hottestDay: state.Reducers631.hottestDay,
    error: state.Reducers631.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchHottestDay: (from, to) => {
      dispatch(fetchHottestDay({from, to}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US631);
