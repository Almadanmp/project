import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchColdDay} from './Actions';
import {Button, Card, CardBody, Collapse} from "reactstrap";
import DatePickerWithTwoDates from "../../DatePickerWithTwoDates";

class US630 extends Component {
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
    this.props.onFetchColdDay(this.state.from, this.state.to);
  }

  handleIntervalPicker = (from, to) => {

    console.log("handleIntervalPicker: from"+ JSON.stringify(from)+ "to: "+ JSON.stringify(to))
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
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    }
    else {
      const{cold} = this.props;
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Get the last coldest
            day (lower maximum temperature) in the house area in a given period. (US630)</Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
                <DatePickerWithTwoDates getDates={this.handleIntervalPicker} numberOfMonths={numberOfMonths}/>
                <h5 key={cold.value}>The coldest day was {cold.value} on the date {cold.date}</h5>
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
