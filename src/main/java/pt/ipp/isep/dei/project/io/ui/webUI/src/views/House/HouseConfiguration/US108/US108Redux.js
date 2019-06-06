import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchGAs } from './Actions';
import {Card, CardBody, Col, Row, Table} from "reactstrap";
import TableHeader from "./TableHeader";
import TableBody from "./TableBody"

class US108Redux extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchUsers();
  }

  render() {
    const headers = {
      name: "Name",
      floor: "Floor",
      height: "Height (m)",
      length: "Length (m)",
      width: "Width (m)"
    };

    const { loading,data } = this.props;
    if (loading === true) {
      return (<h1>Loading ....</h1>);
    } else {
      return (
        <div className="animated fadeIn">
          <Row>
            <Col xs="12" lg="6">
              <Card>
                <CardBody>
                  <Table responsive>
                    <TableHeader headers={headers}/>
                    <TableBody data={data} />
                  </Table>
                </CardBody>
              </Card>
            </Col>
          </Row>
        </div>
      );

      }
      }


}

const mapStateToProps = (state) => {
  return {
      loading: state.Reducer108.loading,
      data: state.Reducer108.data,
      error: state.Reducer108.error
    }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchUsers: () => {
      dispatch(fetchGAs())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US108Redux);
