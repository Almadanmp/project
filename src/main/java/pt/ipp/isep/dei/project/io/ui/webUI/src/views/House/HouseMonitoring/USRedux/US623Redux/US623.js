import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Button, Card, CardBody, Collapse} from "reactstrap";
import DatePickerWithTwoDates from "../../DatePickerWithTwoDates";
import {fetchRainfall} from "./Actions623";

class US623 extends Component {
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
    this.props.onFetchAverageRainfall(this.state.from, this.state.to);
  }

  handleIntervalPicker = (from, to) => {

    console.log("handleIntervalPicker: from" + JSON.stringify(from) + "to: " + JSON.stringify(to))
    if (from !== undefined && to !== undefined) {
      const initialDay = from.toISOString().substring(0, 10);
      const finalDay = to.toISOString().substring(0, 10);
      this.setState({from: from, to: to});
      this.props.onFetchAverageRainfall(initialDay, finalDay)

    }
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    const numberOfMonths = 2;
    const {loading} = this.props;
    const {rainfall} = this.props;
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    }
    return (
      <div>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.toggle}
                style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Average rainfall:</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <DatePickerWithTwoDates getDates={this.handleIntervalPicker} numberOfMonths={numberOfMonths}/>
              <h5 key={rainfall}>The average rainfall was {rainfall}</h5>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducers623.loading,
    amplitude: state.Reducers623.rainfall,
    error: state.Reducers623.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchAverageRainfall: (from, to) => {
      dispatch(fetchRainfall({from, to}))
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US623);
