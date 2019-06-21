import React, {Component} from 'react';
import {Button, Card, CardBody, Collapse} from "reactstrap";
import ImportAreaReadingsDropzone from "./AreaReadingsDropzone";
import {connect} from "react-redux";

class ImportGAReadings extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false, name: 'area readings'};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    if (this.props.loading === true) {
      return (
        <div align="center">
          <p><font size="5">LOADING</font> </p>
          <p><font size="4">. . . please wait . . .</font> </p>
          <div align="center">
            <div className="spinner-border" role="status">
              <span className="sr-only"> Loading...</span>
            </div>
          </div>
        </div>
      );
    } else {
      return (
        <div>
          <Button onClick={this.toggle} style={{backgroundColor: '#93c4c4', marginBottom: '2rem'}}
                  class="fa fa-plus-square-o fa-lg mt-4">Import Area Readings </Button>
          <Collapse isOpen={this.state.collapse}>
            <Card>
              <CardBody>
              <span>
              <ImportAreaReadingsDropzone/>
              </span>
              </CardBody>
            </Card>
          </Collapse>
        </div>
      )
    }
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.ReducersAreaReadings.loading,
  }
};


export default connect(
  mapStateToProps,
  null,
)(ImportGAReadings);

